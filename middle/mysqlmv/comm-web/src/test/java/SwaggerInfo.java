//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Maps;
//import io.swagger.models.Model;
//import io.swagger.models.Path;
//import io.swagger.models.Response;
//import io.swagger.models.Swagger;
//import io.swagger.models.parameters.BodyParameter;
//import io.swagger.models.parameters.Parameter;
//import io.swagger.models.properties.Property;
//import io.swagger.parser.SwaggerParser;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class SwaggerInfo {
//	private static final String REMOTE_REF_JSON = "http://petstore.swagger.io/v2/swagger.json#/definitions/Tag";
//
//	public static void main(String[] args) {
//
//		String resourcePath = SwaggerInfo.class.getResource("/").getPath()
//				+ "swagger/api-docs.json";
//		 resourcePath = "http://localhost:8080/eventweb/v2/api-docs";
//
//		Swagger swagger = new SwaggerParser().read(resourcePath);
//
//		parseUrl(swagger);
//
//		Map<String, Path> paths = swagger.getPaths();
//
//
//		Path apiPath = null;
//		apiPath = paths.get("/events/push");
//
//		// 获取请求的 swagger参数列表
//		List<Parameter> parmList = apiPath.getPost().getParameters();
//
//		System.out.println("parmList"+JSON.toJSON(parmList));
//
//		Map<String, Response> responses = apiPath.getPost().getResponses();
//
//
//		Parameter parm = parmList.get(0);
//
//		// 强制转换成body参数
//		Map<String,String> bodyParamMap = Maps.newHashMap();
//		BodyParameter bodyParam = new BodyParameter();
//		if(parm.getIn().equals("body")){
//			bodyParam = ((BodyParameter)parm);
//		}else{
//			System.out.println("not valid post body");
//			return ;
//		}
//
//
//		// 获取请求体的类型
//		Model schema = bodyParam.getSchema();
//		System.out.println("schema:" + JSON.toJSONString(schema));
//
//		// GenericRef(RefType.PATH,referenceStr);
//		String referenceStr = schema.getReference();
//		System.out.println("reference：" + referenceStr);
//
//		String simpleReference = referenceStr.substring(referenceStr.lastIndexOf("/") + 1 );
//		System.out.println("simpleReference:" + simpleReference);
//
//		// 获取请求体及参数
//		Model bodyParamSchema = swagger.getDefinitions().get(simpleReference);
//		BodyParameter bodyParameter = new BodyParameter()
//				.schema(bodyParamSchema);
//
//		// 获取请求实体的参数
//		Map<String, Property> bodyProperty = bodyParameter.getSchema()
//				.getProperties();
//		for (Entry<String, Property> propertiesEntry : bodyProperty.entrySet()) {
//			System.out.println(propertiesEntry.getKey() + " "
//					+ propertiesEntry.getValue().getType());
//			String type = propertiesEntry.getValue().getType();
//			if(type.equals("ref")){
//
//			}
//		}
//
//
//		parseResponse(swagger,responses);
//		// for(Entry<String, Path> tmp : paths.entrySet()){
//		// System.out.println(tmp.getKey());
//		// }
//	}
//
//
//	public static void parseUrl(Swagger swagger ){
//		Map<String, Path> paths = swagger.getPaths();
//		for (Entry<String, Path > path : paths.entrySet()) {
//			System.out.println(path.getKey());
//		}
//	}
//
//
//	/**
//	 * 解析post请求体
//	 */
//	public static void parsePostReqBody(Swagger swagger , BodyParameter bodyParam){
//		// 强制转换成body参数
//		Map<String,HashMap<String,String>> bodyParamMap = Maps.newHashMap();
//
////		BodyParameter bodyParam = new BodyParameter();
////		if(parm.getIn().equals("body")){
////			bodyParam = ((BodyParameter)parm);
////		}else{
////			System.out.println("not valid post body");
////			return ;
////		}
//
//
//		// 获取请求体的类型
//		Model schema = bodyParam.getSchema();
//		System.out.println("schema:" + JSON.toJSONString(schema));
//
//		// GenericRef(RefType.PATH,referenceStr);
//		String referenceStr = schema.getReference();
//		System.out.println("reference：" + referenceStr);
//
//		String simpleReference = referenceStr.substring(referenceStr.lastIndexOf("/") + 1 );
//		System.out.println("simpleReference:" + simpleReference);
//
//		// 获取请求体及参数
//		Model bodyParamSchema = swagger.getDefinitions().get(simpleReference);
//		BodyParameter bodyParameter = new BodyParameter()
//				.schema(bodyParamSchema);
//
//		// 获取请求实体的参数
//		Map<String, Property> bodyProperty = bodyParameter.getSchema()
//				.getProperties();
//		for (Entry<String, Property> propertiesEntry : bodyProperty.entrySet()) {
//			System.out.println(propertiesEntry.getKey() + " "
//					+ propertiesEntry.getValue().getType());
//			String name = propertiesEntry.getKey();
//			String type = propertiesEntry.getValue().getType();
//			if(type.equals("string")){
////				bodyParamMap.put(name,"name");
//			}
//		}
//
//	}
//
//	public static void parseResponse(Swagger swagger, BodyParameter bodyParam){
//	}
//
//	// 解析返回参数
//	public static void parseResponse(Swagger swagger, Map<String, Response> responses ){
//
//		Response response = responses.get("200");
//		System.out.println("response:" + JSON.toJSONString(response));
//		 Property schema = response.getSchema();
//		System.out.println("schema:" + JSON.toJSONString(schema));
//
//		// GenericRef(RefType.PATH,referenceStr);
////		String referenceStr = schema.getReference();
////		System.out.println("reference：" + referenceStr);
////
////		String simpleReference = referenceStr.substring(referenceStr.lastIndexOf("/") + 1 );
////		System.out.println("simpleReference:" + simpleReference);
//
//
//	}
//
//	public static void t() {
//		Swagger swagger = new SwaggerParser()
//				.read("http://petstore.swagger.io/v2/swagger.json");
//		System.out.println(swagger.getHost());
//		Map<String, Path> paths = swagger.getPaths();
//
//		for (Entry<String, Path> tmp : paths.entrySet()) {
//			System.out.println(tmp.getKey());
//		}
//
//		System.out.println(paths.get("/pet").getParameters());
//
//		Map<String, Model> definitions = swagger.getDefinitions();
//
//		for (Entry<String, Model> entry : definitions.entrySet()) {
//			System.out.println(entry.getKey());
//			Model model = entry.getValue();
//			Object a = model.getExample();
//			Map<String, Property> properties = model.getProperties();
//			for (Entry<String, Property> propertiesEntry : properties
//					.entrySet()) {
//				System.out.println(propertiesEntry.getKey() + " "
//						+ propertiesEntry.getValue().getName());
//
//			}
//			System.out.println(JSON.toJSONString(a));
//		}
//
//		// swagger = new Swagger();
//		// swagger.path("/fun", new Path()
//		// .get(new Operation()
//		// .parameter(new BodyParameter()
//		// .schema(new RefModel(remoteRef)))));
//		//
//		// final Swagger resolved = new SwaggerResolver(swagger,
//		// null).resolve();
//		// final BodyParameter param = (BodyParameter)
//		// swagger.getPaths().get("/fun").getGet().getParameters().get(0);
//		// final RefModel ref = (RefModel) param.getSchema();
//	}
//
//}
