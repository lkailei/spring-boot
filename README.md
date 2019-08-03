# spring-boot
spring-boot的基本的使用方式
# springboot:
			Spring Boot是他们最新的创新，能够跟上不断变化的技术需求。 开发Spring Boot的主要动机是简化配置和部署spring应用程序的过程。
			Spring Boot为开发提供一个具有最小功能的Spring应用程序，并提供了一个新的范例。
			使用Spring Boot将能够以更灵活的方式开发Spring应用程序，并且能够通过最小(或可能没有)配置Spring来专注于解决应用程序的功能需求。它使用全新的开发模型，通过避免一些繁琐的开发步骤和样板代码和配置，使Java开发非常容易。
		Spring Boot的主要特点：
				创建独立的Spring应用程序
				直接嵌入Tomcat，Jetty或Undertow（无需部署WAR文件）
				提供“初始”的POM文件内容，以简化Maven配置
				尽可能时自动配置Spring
				提供生产就绪的功能，如指标，健康检查和外部化配置
				绝对无代码生成，也不需要XML配置

		设置springBoot的banner：
		我们在 src/main/resources 目录下新建一个 banner.txt
					${AnsiColor.BRIGHT_RED}：设置控制台中输出内容的颜色
					${application.version}：用来获取 MANIFEST.MF 文件中的版本号
					${application.formatted-version}：格式化后的 ${application.version} 版本信息
					${spring-boot.version}：Spring Boot 的版本号
					${spring-boot.formatted-version}：格式化后的 ${spring-boot.version} 版本信息
		springboot的核心还是spring，他不是编写应用程序的框架，他是提供应用程序服务器功能的嵌入式servlert容器。
