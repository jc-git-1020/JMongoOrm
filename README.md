## 一，什么是对象关系映射 ORM ##
百度百科：

> 对象关系映射（英语：(Object Relational Mapping，简称ORM，或O/RM，或O/R mapping），是一种程序技术，用于实现面向对象编程语言里不同类型系统的数据之间的转换 [1]  。从效果上说，它其实是创建了一个可在编程语言里使用的--“虚拟对象数据库”。

简单粗暴的理解就是，把数据库中一张张数据表跟一个个对象关联起来，例如，数据库中有一张person表，有name，age两个字段，相对应的，代码层有一个person类，同样有两个变量，我们通过操作person类的增删改查进而实现对person表的增删改查，而不是编写繁琐的sql语句。

**把抽象的数据库操作具象化，结构化。**

常见的orm框架有

Java：mybatis，hibernate

C#：dapper，EntityFramework，Nhibernate


----------
## 二，ORM框架的优劣 ##
优点：

 1. 一定程度上可以防止SQL注入（是一定程度，不是绝对）。
 2. 方便使用面向对象，增删改查通过代码动态实现，代码优雅，学习成本低，开发成本低，方便团队协作，提高开发效率。
 3. 封装业务逻辑，隐藏数据库操作，方便重构或者更换数据库，只需要修改orm框架的底层sql映射，不需要整个项目重写。
 4. 方便设置钩子函数，比如分页里面，拿分页数据同时要count数据，那么就可以在Model里面插入这个算count的钩子函数（包括缓存逻辑）。
 5. 可以在代码层实现缓存功能，达到一定程度性能优化。


缺点：

 1. 隐藏底层sql，让开发者对sql的理解不够直观。
 2. 如果orm框架编写不够完善，无法很好的映射各种sql语句。
 3. 性能较直接用SQL差。

个人观点：

 1. 我个人比较倾向于在项目中使用orm框架，优点如上所说。
 2. 如果项目中遇到无法用orm实现的sql，可以使用传统的方式进行数据库操作。
 3. 对于现在的计算机，硬件水平已经很强大，只要是经过测试验收的orm框架，都不会存在太大的性能问题，如果牺牲5%的性能，能让开发效率提升20%，我觉得是很值得的，使用orm更加方便开发人员的日常维护。
 4. 个人经验，如果一开始不使用orm框架，随着业务复杂度提高，最后都发展出自己的一套残缺版orm。
 5. 对于一些特殊的场景，例如对性能有高要求的，可以使用传统的原生sql进行操作。


----------
## 三，为什么写这个项目 ##
首先，本人在工作中经常使用到MongoDB，也遇到了一些坑。

首先，mongodb无schema的特点大大加快了日常的开发速度，也带来了一些问题。
1，如果前期没有好好规划，随着业务的深入，字段会越来越杂乱，甚至出现爆炸式增长。
2，相对于mysql等关系型数据库，会出现同一个字段字段类型不同的情况，给代码的编码埋下坑。
3，项目使用map作为映射，代码编写不够优雅。

对于MongoDB官方提供的驱动http://mongodb.github.io/mongo-java-driver/3.7/，
有两种操作方式，一种是通过map进行字段的检索，这个不够面向对象，另一种是映射pojo类，但是只能映射简单的pojo，MongoDB是文档型数据库，很容易出现各种内嵌文档，此时这个方式就明显不够友好。

所以，我萌生了自己写一个简单的orm框架的想法，一来是实际工作中真的遇到了这种场景，其次是对自己技能的一次锻炼和提升。

----------
## 四，框架描述 ##
同时附带一个测试案例，通过执行测试案例，就能很清晰的知道操作方式。

框架很轻，核心类很少
![这里写图片描述](https://img-blog.csdn.net/20180504142221168?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NoZW5fVmljdG9y/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

本质上是对mongodb官方Java驱动的一次封装，通过自定义注解标注需要映射的字段，通过递归实现嵌套文档的映射，通过反射实现map到具体实体类的转化。

使用的时候，用户需要自己通过继承dao抽象类，实现具体数据表的dao对象。

```
public class All_Types_ModelDao extends Dao<All_Types_Model> {

    public All_Types_ModelDao() {
    }

    @Override
    public String getCollectionName() {
        return "all_types_model";
    }

    @Override
    public Class<All_Types_Model> getModelClass() {
        return All_Types_Model.class;
    }

}

```
然后每一个表对应一个或多个继承model的实体类，如果有内嵌文档的话就需要有多个model的子类，例如下面需要定义多一个person实体类，而且每个实体类必须要有一个无参的构造函数，反射的时候需要用到。

```
public class All_Types_Model extends Model {

    @MongoObjectId
    private ObjectId id;
    @MongoSimple(name = "StringField")
    private String StringField;
    @MongoSimple(name = "ArrayField")
    private ArrayList<Integer> ArrayField;
    @MongoSimple(name = "BoolField")
    private Boolean BoolField;
    @MongoSimple(name = "DateField",ignoreIfNull = true)
    private Date DateField;
    @MongoSimple(name = "DoubleField")
    private Double DoubleField;
    @MongoSimple(name = "Int32Field")
    private Integer Int32Field;
    @MongoSimple(name = "Int64Field")
    private Long Int64Field;
    @MongoSimple(name = "TimestampField")
    private BsonTimestamp TimestampField;
    @MongoObject(name = "person")
    private Person person;
    @MongoObjects(name = "persons")
    private ArrayList<Person> persons;

    public All_Types_Model() {
    }
	
	...

 }
```
其中有四种特定的变量注解
MongoObjectId：对应objectid。
MongoSimple：普通的字段，需要传入变量对应的数据表中字段的字段名。
MongoObject：内嵌文档字段，需要传入变量对应的数据表中字段的字段名。
MongoObjects：内嵌文档集合字段，需要传入变量对应的数据表中字段的字段名。

```
@MongoSimple(name = "DateField",ignoreIfNull = true)
    private Date DateField;
```
ignoreIfNull表示如果字段为null的时候，insert数据的时候该字段不插入。

提供支持链式编程的FilterBuilder，UpdateBuilder，ProjectBuilder，SortBuilder，对应不同的功能。

```
		//支持链式编程
        FilterBuilder fb = new FilterBuilder();
        fb.eq(stringField, new BsonString(str)).eq(intergerField, new BsonInt32(i));
        long count = dao.deleteOne(fb).getDeletedCount();
        System.out.println(count);
```


----------
## 五，不足之处 ##
1，暂时没有覆盖mongodb支持的全部字段，后面可以根据实际需要进行补充。
目前支持的字段如下
```
	@MongoObjectId
    private ObjectId id;
    @MongoSimple(name = "StringField")
    private String StringField;
    @MongoSimple(name = "ArrayField")
    private ArrayList<Integer> ArrayField;
    @MongoSimple(name = "BoolField")
    private Boolean BoolField;
    @MongoSimple(name = "DateField",ignoreIfNull = true)
    private Date DateField;
    @MongoSimple(name = "DoubleField")
    private Double DoubleField;
    @MongoSimple(name = "Int32Field")
    private Integer Int32Field;
    @MongoSimple(name = "Int64Field")
    private Long Int64Field;
    @MongoSimple(name = "TimestampField")
    private BsonTimestamp TimestampField;
    @MongoObject(name = "person")
    private Person person;
    @MongoObjects(name = "persons")
    private ArrayList<Person> persons;
```

2，这个小项目是本人私下练手写的，还未经过实际项目实战的考验，可能还有一些写的不好的地方需要日后进行完善。

3，才疏学浅，欢迎大家拍砖讨论。
