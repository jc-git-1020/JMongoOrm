package db.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoObjects {
    String name();
    //变量为空时数据库是否插入null值
    boolean ignoreIfNull() default false;
}
