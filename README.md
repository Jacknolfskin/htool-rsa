# htool-rsa
SpringBoot RSA入参出参自动加解密工具

####使用方法
将项目下载下来：https://github.com/Jacknolfskin/htool-rsa
* 上传到本地仓库
![image.png](https://upload-images.jianshu.io/upload_images/11222983-6327681a4c97155c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 在项目中引入依赖
**compile('com.jacknolfskin.htool:htool-rsa:1.0.0-RELEASE')**
**注意：这两个依赖也要引入，Base64的依赖包，不然加密会报错** 
**compile group: 'commons-codec', name: 'commons-codec', version: '1.11'
compile group: 'commons-io', name: 'commons-io', version: '2.6'**
![image.png](https://upload-images.jianshu.io/upload_images/11222983-34c50f5d567190d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 在启动类中加入**@EnableSecurity**注解，同时扫描包**com.jacknolfskin.htool**
![image.png](https://upload-images.jianshu.io/upload_images/11222983-5cb62242e0b74586.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 在出参需要加密的方法上加上注解**@Encrypt**，在入参需要解密的方法上加上**@Decrypt**，同时需要加解密就把两个注解都加在方法上
![image.png](https://upload-images.jianshu.io/upload_images/11222983-024781547a4b885b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 配置文件中**spring.encrypt.debug、spring.encrypt.publicKey、spring.encrypt.privateKey**三个配置选项
![image.png](https://upload-images.jianshu.io/upload_images/11222983-19ac52e1bd7e5b8e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

* 使用后效果
![image.png](https://upload-images.jianshu.io/upload_images/11222983-94c9876a6cc53de6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


使用教程地址：https://www.jianshu.com/p/722cf8525eea

---
#### 技术讨论 & 疑问建议 & [个人博客](https://www.jacknolfskin.top/)
**版权声明**: 本博客所有文章除特别声明外，均采用 [CC BY-NC-SA 3.0](https://creativecommons.org/licenses/by-nc-sa/3.0/) 许可协议，转载请注明出处！





