## IOC基本概念
1. IOC，思想是发展资源获取方向，传统资源查找方式要求组件向容器发起请求查找资源，作为回应，容器适时返回资源，而IOC是容器主动将资源推送给所管理的组件，组件需要做的是接收资源
2. DI，这是IOC的另一种表示，组件以一些预定义好的方式接收容器的资源注入

## Spring容器
&emsp;&emsp;Spring容器：在Spring IOC容器读取bean配置创建bean之前，必须对容器进行实例化，只有在容器实例化之后，才可以从IOC容器中获取bean的实例并应用，Spring提供了两种类型的IOC容器实现，分别是IOC容器的基本实现BeanFactory，这是Spring框架的基础设施，面向Spring本身；BeanFactory的子接口ApplicationContext，该接口面向Spring的开发者；无论使用哪种方式，其配置文件是相同的

## ApplicationContext的主要实现类
![ApplicationContext的主要实现类](F:\Spring4教程\ApplicationContext的主要实现类.png)
1. ClassPathXmlApplicationContext是从类路径下加载配置文件
2. FileSystemXmlApplicationContext是从文件系统中加载配置文件
3. ConfigurableApplicationContext扩展了ApplicationContext，主要新增了refresh()和close()方法，使得ApplicationContext具有启动、刷新、关闭上下文的能力
4. **ApplicationContext在初始化的时候会实现所有单利的bean**
5. WebApplicationContext专门用于WEB应用，允许从相对于WEB根目录的路径完成初始化工作

## Spring 依赖注入的方式
1. 属性注入，就是通过setter方法注入bean的属性或者依赖对象，属性注入使用<property>标签，使用name属性或者<value>子节点指定属性值
2. 构造方法注入，通过构造方法注入bean的属性或者依赖，保证bean实例化之后就可以使用，在<constructor-arg>标签内声明属性，该标签没有<name>属性，直接通过value属性注入，主要通过参数顺序index属性、参数类型type属性实现，
3. 工厂方法注入，很少使用

## Spring 字面值注入与引用注入
1. 字面值，可以通过<value>元素标签或者value属性使用字符串表示的值，基本数据类型及其包装类、String等类型都可以采用字面值的注入方式，如果字面值内包含特殊字符，需要在value子节点中使用<![CDATA[]]>把字符包裹
2. 引用其他bean，通过<ref>元素或者ref属性为bean的属性或者构造器指定bean的引用，也可以在属性或者构造器里包含bean的声明，即内部bean，对于内部bean，是不能被外部引用的
3. 注入null值时，可以使用专用的</null>为Bean的字符串或者其他对象属性注入null，其实属性的默认值就是null，不注入也可以
4. 级联属性，即一个bean中对引用的另一个bean的属性进行赋值，引用bean的名字.属性，注意，级联属性赋值时要求所引用的bean先初始化，此时Spring不会自动为引用的bean创建一个bean
5. 集合属性，Spring有一套内置的集合属性标签，如<list>/<map>/<set>/<property>,分别对应java中的List、Map、Set、Property，内部具体的元素可以使用<value>标签指定常量值、<ref>指定引用的bean、通过<bean>指定内置的bean定义、<null/>置空，对于属性类型时数组的，等同于List；对于map，通过<map>标签定义，<map>里面可以有多个<entry>作为字标签，每个条目包含一个key和一个value，必须在<key>标签里定义key，由于k-v没有类型的限制，可以自由指定他们的<value>/<ref/><bean>/<null>元素；对于java.util.Properties，使用<props>便签，内部使用多个<pro>标签，每个<prop>标签必须定义key属性
6. 5中配置的集合都是配置在内部的，别的bean时不能引用该集合的，因此为了便于引用，需要将集合单独配置成一个bean，这里需要引入**util命名空间**


## 自动装配
Spring的IOC容器允许自动装配，需要做的是早<bean>的autowire属性指定自动装配的模式
1. byType，根据类型自动装配，如果容器中存在多个与目标bean类型相同的bean，此时Spring无法判断，将不能执行自动装配
2. byName，根据名称自动自动装配，此时必须将目标bean的名称和属性设置的完全相同，因此这里命名方式最好符合POJO命名规范，有匹配的自动装配，否则置为null
3. constructor，通过构造器自动装配，当bean中存在多个构造器时，自动装配方式很复杂，不推荐使用

## 自动装配的缺点
1. 因为autowire是bean级别的元素，因此如果设置的自动装配，会自动装配**所有**的属性，因此想装配个别属性时就不够灵活
2. autowire的自动装配机制，要么byName，要么byTpe，两种不能混用
3. 实际项目中，为了明确清晰的配置，一般不使用自动装配

## bean之间的关系
bean之间的关系分为依赖和继承，注意这里的依赖和继承指的不是java中的依赖和继承，指的是bean配置上的依赖和继承
1. 继承，Spring允许继承bean的配置，被继承的bean称为父bean，继承父bean的bean称为子bean；子bean将继承父bean的配置，子bean也可以覆盖父bean的配置，实现个性化；父bean可以作为模板，也可以作为bean实例，如果只想父bean作为模板，可以**将父bean的abstract属性设置为true，即抽象bean，此时Spring就不会实例化父bean**，只有抽象bean可以不指定class；需要注意的是，子bean并不会继承父bean的所有属性，例如autowire、abstract；**子bean的class可以忽略父bean的类型，让子bean指定自己的类，但继承与父bean相同的属性，但此时父bean的abstract必须设置为true**
2. 依赖，Spring允许用户通过**depends-on**属性指定bean的前置依赖bean，前置依赖的bean将先于本bean之前实例化；如果配置了多个前置依赖bean，bean之间通过逗号或空格分隔；

## bean的作用域
默认情况下，在IOC容器中的bean是单例的，每次产生的bean都是同一个实例；Spring中bean的作用域主要有4种
1. singleton，单例的，在整个IOC生命周期内，每次都返回同一个bean，并且**在创建容器时就已经实例化了**
2. prototype，原型的，每次请求返回不同的bean，**IOC容器创建时，不会实例化该bean**
3. request，请求的，这是web中特有的，作用于一个请求
4. session，会话的，这时web中特有的作用域，作用于一个会话

## 使用外部属性文件
&emsp;&emsp;在配置文件中配置bean时，有时需要在bean的配置里混入**系统部署的细节信息**，例如文件路径、数据源，这些部署细节本质上和bean的配置无关，需要和bean的配置文件分离；**Spring提供了PropertyPalceHolderConfiguer的BeanFactory后置处理器**，该处理器允许用户在Bean的配置文件中使用**${var}**占位符，PerpertyPlaceHolderConfiguer从属性文件中加载属性，并用实际值替换变量；Spring还允许在属性文件中使用${propName}以实现属性之间的相互引用；在xml中，需要做的是**引入<context:property-placeholder>，指定location属性，这里需要引入context命名空间**
```
	<!-- 导入配置文件 -->
	<context:property-placeholder location="classpath:db.properties"/>
	<!-- 使用外部文件中定义的属性 -->
	<bean id = "dataSource2" class = "com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="user" value = "${user}"></property>
		<property name="password" value = "${password}"></property>
		<property name="driverClass" value = "${deriverClassr}"></property>
		<property name="jdbcUrl" value = "${jdbcUrl}"></property>
	</bean>
```

## SpEL
Spring 的表达式语言，简称SpEL，是一个支持运行时查询和操作对象图的表达式语言，为bean使用动态配置提供了遍历；使用#{.......}作为界定符，花括号中的所有内容都被认为时SpEL的一部分；使用SpEL可以实现
1. 通过bean的id对bean进行引用；
2. 调用bean的方法以及**bean的属性**；
3. 计算表达式的值；
4. 正则表达式匹配
SpEL的语法如下
1. 配置字面值，#{字面值}，对于String对象，使用单引号或者双引号作为界定符，即#{'aaa'}和#{"aaa"}都是可以的，为避免XML解析报错，最好使用单引号，对于Boolean，#{true/false}
2. 引用bean、属性、方法
    * 通过id引用其他bean，#{bean的id}
    * 引用对象的属性，#{bean的id.属性名}
    * 引用bean的方法，这里支持链式操，例如 #{someBean.toString().toUpperCase()}
3. SpEL支持的运算符
    * 算数运算符， '+ - * / %' 操作，其中 '+' 支持对字符串的拼接；
    * 关系运算符， '< or lt'、'> or gt'、'== or eq'、'>= or ge'、'<= or le'
    * 逻辑运算符，注意这里为了避免冲突，最好使用 and 、 or、 not、 |
    * if-else运算符， '表达式1 ? 表达式2 : 表达式3'、'表达式1 ?: 表达式'
    * 正则表达式，  matches，例如 #s{string **matches** '正则表达式'}
4. 调用静态属性和方法，通过T运算符调用类的静态属性或方法，即T(全限定名).静态方法or属性，例如 value = "#{T(java.lang.Math).PI}"

## Bean的生命周期


