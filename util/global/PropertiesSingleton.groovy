class PropertiesSingleton {

	private static PropertiesSingleton instance;
	private static Properties properties

	private PropertiesSingleton(){
	}

	private static void loadPropertiesFile(){
		properties = new Properties()
		File propertiesFile = new File("/ScholarOne/properties/jenkins.properties")
		InputStream inStream =null
		try{
			inStream= propertiesFile.newDataInputStream()
			properties.load(inStream)
		}finally {
			inStream.close()
		}
	}
	public static PropertiesSingleton getInstance(){
		if(instance == null){
			instance = new PropertiesSingleton();
		}
		return instance;
	}
	public static String getValue(String key){
		PropertiesSingleton propsSingleton=PropertiesSingleton.getInstance()
		if(properties==null)
			propsSingleton.loadPropertiesFile()
		return properties.getProperty(key)
	}
}
