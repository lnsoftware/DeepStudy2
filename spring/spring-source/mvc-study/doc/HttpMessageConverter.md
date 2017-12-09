### 问题引出

```java
 @RequestMapping(value = "formPost", method = RequestMethod.POST,produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User formPost(@RequestParam("id") Long id,@RequestParam("name") String name) {
        log.info("req, userId:{}",id);
        User u = new User();
        u.setId(id);
        u.setName(name);
        return u;
    }
```

```shell
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" 'http://localhost:8080/formPost' -d 'id=1&name=foo&mobile=13612345678'
```

通过上面URL请求数据的时候，返回一直报错。

### HttpMessageConverter说明

HttpMessageConverter接口指定了一个可以把Http request信息和Http response信息进行格式转换的转换器。通常实现HttpMessageConverter接口的转换器有以下几种：

ByteArrayHttpMessageConverter: 负责读取二进制格式的数据和写出二进制格式的数据；

StringHttpMessageConverter：   负责读取字符串格式的数据和写出二进制格式的数据；

ResourceHttpMessageConverter：负责读取资源文件和写出资源文件数据； 

FormHttpMessageConverter：       负责读取form提交的数据（能读取的数据格式为 application/x-www-form-urlencoded，不能读取multipart/form-data格式数据）；负责写入application/x-www-from-urlencoded和multipart/form-data格式的数据；

MappingJacksonHttpMessageConverter:  负责读取和写入json格式的数据；

SourceHttpMessageConverter：                   负责读取和写入 xml 中javax.xml.transform.Source定义的数据；

Jaxb2RootElementHttpMessageConverter:  负责读取和写入xml 标签格式的数据；

AtomFeedHttpMessageConverter:              负责读取和写入Atom格式的数据；

RssChannelHttpMessageConverter:           负责读取和写入RSS格式的数据；

### 配置


**````**http://blog.csdn.net/mickjoust/article/details/51671060

### 代码分析

org.springframework.http.converter.FormHttpMessageConverter.canWrite
```java
@Override
public boolean canWrite(Class<?> clazz, MediaType mediaType) {
    if (!MultiValueMap.class.isAssignableFrom(clazz)) {
        return false;
    }
    if (mediaType == null || MediaType.ALL.equals(mediaType)) {
        return true;
    }
    for (MediaType supportedMediaType : getSupportedMediaTypes()) {
        if (supportedMediaType.isCompatibleWith(mediaType)) {
            return true;
        }
    }
    return false;
}
```

FormHttpMessageConverter的canWrite仅仅``支持controller返回值为MultiValueMap类型数据，所以不可以。

FastJsonHttpMessageConverter.canWrite支持任何类型class和任何类型的mediatype
所以配置FastJsonHttpMessageConverter后问题解决。

### 深入分析

处理请求参数
RequestResponseBodyMethodProcessor.resolveArgument调用readWithMessageConverters找到匹配MessageConvert进行处理

处理返回值
RequestResponseBodyMethodProcessor.handleReturnValue，主要是调用writeWithMessageConverters，找到匹配的MessageConvert处理返回值。


### SpringBoot

```java

spring:
  http:
    converters:
      preferred-json-mapper: fastjson

```

messageconvert自动配置类：org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration


### 参考

http://blog.csdn.net/zmx729618/article/details/54137240
