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
Spring IOC容器对bean的生命周期管理过程
1. 通过构造方法或者工厂方法创建bean的实例
2. 为bean的属性设置值和对其他bean的引用
3. 调用bean的初始化方法
4. bean可以被调用
5. 当容器关闭时，调用bean的销毁方法
6. 在bean的声明里设置init-method和destroy-method属性，可以为bean指定初始化和销毁方法

&emsp;&emsp; bean的后置处理器允许调用初始化方法前后对bean进行额外处理；后置处理器将对容器中的**所有类型的bean实例**进行逐一处理，不只是某个bean或者某个类型的bean，一个典型的应用就是检查bean属性的正确性或者根据特定标准更改bean的属性；由于后置处理器是处理所有的bean的，为此一般需要对bean进行过滤，达到处理某些bean的目的；应用上，bean需要实现BeanPostProcessor接口，在初始化前后，Spring将吧每个bean实例分别送给接口定义的方法
    * public Object postProcessAfterInitialization(Object bean, String beanName);
	* public Object postProcessBeforeInitialization(Object bean, String beanName);
配置上，不需要配置id，只需要配置class属性即可，Spring容器会自动识别这是一个BeanPostProcessor；对于返回值，一般需要返回处理后的bean，可以是原来的bean，也可以是修改后的bean，甚至可以是新创建的bean，即bean的后置处理器甚至可以换掉原来的bean；这时，bean的生命周期过程如下
1. 通过构造方法或者工厂方法创建bean实例
2. 设置bean的属性和bean之间的引用
3. 将bean实例传递给bean后置处理器的postProcessBeforeInitialization方法
4. 调用bean的初始化方法
5. 将bean实例传递给bean的后置处理器postProcessAfterInitialization方法
6. bean可以被调用
7. 当容器关闭时，调用bean的销毁方法

## 通过工厂方法配置bean——静态工厂和实例工厂
&emsp;&emsp;调用静态工厂方法创建bean是将对象的创建过程封装到静态方法中，用户在需要对象时，只需要简单的调用静态方法，不需要关注bean的创建细节；在声明通过静态方法创建bean时，**需要在bean的class属性中指明拥有该工厂方法的类，同时在factorymethod属性中指定工厂方法的名称，最后使用<constructor-arg>元素对工厂传递参数**
```
静态工厂方法
	<!--   通过静态工厂方法配置bean实例，注意这里不是配置静态工厂方法实例 -->
	<bean id = "car1" class = "cn.seu.edu.factory.StaticCarFactory" factory-method="getCar">
		<constructor-arg value = "Audi"></constructor-arg>
	</bean>

实例工厂方法
	<!-- 因为实例工厂方法需要创建后才能获取实例，这里需要先配置实例工厂的bean -->
	<bean id = "carInstanceFactory" class = "cn.seu.edu.factory.InstanceCarFactory"></bean>
	<!-- 通过实例工厂配置bean --> 
	<bean id = "car2" factory-bean="carInstanceFactory" factory-method="getCar">
		<constructor-arg value = "Ford"></constructor-arg>
	</bean>
```

## 通过FactoryBean配置
&emsp;&emsp;通过FactoryBean配置bean时，需要实现FactoryBean接口，重写接口方法，一般用于框架整合
```
	<!-- 通过FactoryBean配置bean时
		 class 指向实现FactoryBean接口的全类名
		 property配置FactoryBean的属性
		 返回的实例是FactoryBean的getObject()返回的实例
	 -->
	<bean id = "car" class = "cn.seu.edu.factorybean.CarFactoryBean">
		<property name="brand" value = "BMW"></property>
	</bean>
```

## 基于注解的方式配置bean与装配bean的属性
&emsp;&emsp;Spring可以在classpath中进行组件扫描，进而侦测和实例化具有特定注解的组件；对扫描到的组件，Spring默认的命名策略是使用非限定类名，且第一个字母小写，当然也可以通过设置组件的value属性指定组件的名称；注意使用注解方式配置bean时，需要引入spring-aop-4.1.5.RELEASE.jar；特定组件包括
1. @Component，一个基本注解，标识了一个受Spring管理的组件
2. @Respository，标识持久层组件
3. @Serivce，标识服务层/业务层组件
4. @Controller， 标识表现层组件
5. 需要注意的是，Spring本质上无法判断组件的类型，因此上述4种组件类型是可以混用的，即可以将@Serivce注解应用于持久层，但为了清晰，任然建议按实际需要选用合适的注解
&emsp;&emsp;默认Spring是不开启组件扫描的，因此在使用Java Config方式需要使用@ComponentScan注解，或者再XML中使用<context:component-scan/>；这两种方式都可以指定扫描范围，以xml配置为例：
1. base-package属性，指定需要扫描的基类包，Spring将自动扫描该基类包及其子包中的所有类，多个基类包时需要使用逗号分隔
2. resource-pattern属性，表名仅希望扫描特定的类，而不是所有的类
3. <context:include-filter>子节点表示需要包含的目标类，**注意这里需要设置<context:component-scan/>节点的use-default-filters属性为false，否则不生效；同时还需要注意，include不仅包含字面指定的类，还包含字面指定的类的子类，例如如果指定目标类为标注了@Component的类，那么标注了@Respository、@Serivce、@Controller注解的类也会包含进去**
4. <context:exclude-filter>子节点表示不需要包含的目标类，**exclude-filter和include-filter在使用上的对比**
    * include-filter必须设置use-default-filters为false，exclude-filter必须设置为true；Spring默认设置为true；
	* include-filter、use-default-filters都包含字面指定的目标类，也包含其子类
5. 一个<context:component-scan>下可以包含多个<context:include-filter>、<context:exclude-filter>
6. <context:include-filter>、<context:exclude-filter>节点中存放表达式，过滤表达式如下：

类别|实例|说明
---|---|---|
annotation | cn.seu.edu.xxxAnnotation | 所有标注了 xxxAnnotation 的类，该类型通过判断目标类是否标注了某个注解进行过滤
assinable | cn.seu.edu.xxxService | 所有继承或者扩展了 xxxService 的类或者接口，该类型通过判断目标类是否扩展或者继承了某个特定的类或者接口进行过滤
aspectj | AspectJ表达式 | 该类型通过AspectJ语法进行过滤
regex | regex表达式 | 该类型通过正则表达式过滤
custom | cn.seu.edu.xxTypeFilter | 采用 xxTypeFilter 通过代码过滤，要求该类必须实现org.springframework.core.TypeFilter接口

<context:component-scan/>元素会自动注册一个AutowiredAnnotationBeanPostProcessor实例，该实例可以用来自动装配的具有@Autowired、@Resource、@Inject注解的属性
@Autowired注解自动装配具有**兼容类型**的单个bean属性
1. 构造器、普通字段、一切具有参数的方法都可以使用@Autowired注解
2. 默认情况下，所有使用了@Autowired注解的属性都需要被设置，当Spring找不到匹配的bean时会抛出异常，如果某属性允许不设置，只需要设置@Autowired的required属性为false即可
3. 默认情况下，当IOC容器中含有多个类型兼容的bean时，自动类型装配将无法工作，此时还需要使用@Qualifier注解，并指定bean的id，当然也可以通过设置组件bean的id方式排除歧义；**Spring允许对方法的入参使用@Qualifier注解**
4. @Autowired注解可以使用在**数组类型**上，此时Spring会将**所有匹配**的bean进行自动装配
5. @Autowired注解可以使用在**集合类型**上，此时Spring会读取集合的类型信息，然后自动装配**所有**与之兼容的bean
6. @Autowired注解如果应用在java.util.Map上，如果Map的**key为String类型**时，此时Spring将自动装配与Map的value类型兼容的bean，此时Map的key就是bean的名称
&emsp;&emsp;至于@Resource和@Inject注解，和@Autowired注解功能类似；@Resource注解根据bean的id进行装配，要求提供bean的id，如果不设置，则默认采用标注处的变量名或者方法名作为bean的id；@Inject和@utowired属性一样都是根据类型匹配注入的，不同之处在于@Inject没有required属性；**建议使用@Autowired注解**

## 泛型依赖注入
&emsp;&emsp;泛型依赖注入式Spring4.X引入的新特性，他可以为bean注入泛型成员对应的引用

## Spring AOP--以下内容均针对Spring实现的AOP而言
&emsp;&emsp;使用动态代理，将对象包裹起来，然后调用代理对象取代原始方法，任何原始对象的调用都要通过代理，由代理对象决定是否以及何时将方法调用转到原始对象，几个基本概念
1. 切面Aspect，跨越应用程序多个模块的功能，是一个被模块化特殊对待的对象，也称横切关注点；可以理解为 Aspect = advice + pointcut
2. 通知advice，切面需要完成的工作，可以认为是切面总的每个方法；可以理解为what + when
3. 目标target， 被通知的对象
4. 代理proxy，向目标应用通知后生成的对象
5. 连接点jointpoint，就是程序执行的某个位置，例如方法调用前、后、正常返回、抛异常、前后等；连接点由两个信息确定：方法表示的程序执行点（仅对Spring而言），即方法，和相对点的方位，即方法调用的前、后等
6. 切点pointcut，每个类都拥有多个连接点，连接点相当于数据库的记录，切点相当于查询条件，aop通过切点定位到连接点，切点和连接点不是一一对应的，一般一个切点对应多个连接点，切点通过org.springframework.aop.PointCut接口进行描述，它使用类和方法作为连接点的查询条件；可以理解为where

## 基于注解的AOP
基于注解的方式，在使用上，首选需要将切面加入IOC容器，使用@Conponent；然后声明切面，使用@Aspect；声明通知类型@Befroe、@After、@AfterReturing、@AfterThrowing、@Around；在xml中加入<aop:aspectj-autoproxy/>启用aop；
1. 通过在通知方法中使用Joinpoint参数，可以访问连接点的细节；
2. @After 后置通知不管方法是否正常结束均会执行，注意在后置通知中不能访问方法执行的结果；
3. @AfterThrowing异常通知中，可以访问异常且可以指定异常类型，此时只有在出现该异常时才执行异常通知，可以理解为catch语句；具体使用上，在注解中增加throwing参数，通知中增加一个同名参数即可
4. @Returing，在返回通知中，只需要将returning属性添加到@Returing注解中，同时在通知中增加一个同名参数，就可以在通知中访问方法返回的结果
5. @Around，一种功能最强的通知类型，可以全面的控制连接点，甚至可以控制是否执行连接点；对于环绕通知，连接点的参数必须是ProceedingJoinPoint，这时JoinPoint的子接口，用于控制何时以及是否执行连接点；在环绕通知中，必须显式调用ProceedingJoinPoint的proceed()方法，用来执行被代理方法，否则被代理方法将不执行；环绕通知中可以需要在proceed()方法执行之后才能获得方法执行结果，否则NPE；话绕通知必须有返回值；可以这么理解，环绕通知就是代理的全过程；
在AOP中，还**可以使用@Oroder(数字值)注解，用于指定切面的优先级，其中值越小，优先级越高**；对于相同的切点表达式，还可以写一个空的方法，用@PointCut注解，然后在上述五种通知的注解中通过对该方法的引用来充当切点表达式，如果通知和切点表达式不再一个包内，加上包名即可

## 基于XML的AOP
```
	<!-- 配置切面的bean -->
	<bean id = "loggingAspect" class = "cn.seu.edu.aop.helloworld.aopxml.LoggingAspect"></bean>
	<!-- 配置AOP -->
	<aop:config>
		<!-- 配置切点表达式 -->
		<aop:pointcut expression="execution(public int cn.seu.edu.aop.helloworld.aopxml.ArithmeticCalculator.*(int, int))" 
			id="loggingAspectExpression"/>
		<!-- 配置切面 -->
		<aop:aspect ref = "loggingAspect" order = "2">
			<!-- 配置通知 -->
			<aop:before method="beforeMethod" pointcut-ref="loggingAspectExpression"/>
			<aop:after method="afterMethod" pointcut-ref="loggingAspectExpression"/>
			<aop:after-returning method="afterReturningMethod" pointcut-ref="loggingAspectExpression" returning="result"/>
			<aop:after-throwing method="afterThrowingMethod" pointcut-ref="loggingAspectExpression" throwing="ex"/>
			<aop:around method="aroundgMethod" pointcut-ref="loggingAspectExpression"/>
		</aop:aspect>
	</aop:config>
```


