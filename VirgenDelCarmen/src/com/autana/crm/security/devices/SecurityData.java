package com.autana.crm.security.devices;

public class SecurityData {
	
	private PublicKeyCrypto cryptor;
	
	protected static String strServerPrivateHex="30820153020100300d06092a864886f70d01010105000482013d308201390201000241008b965720dcb8ddb3bf063be1cefc729f56843989d8c25f8ef3fc493ef2b65b9727ccad9c027258a2c8cebc4cf4e6d0a4a1bdd457cec116de44f7764aefec0473020301000102404ab6fb05fcfefab4467048c7322a9013d79448d40ef0e38339f775213b7afe849c7b499417a8484b58e969104bdefd23af62bf62bc972341562d807326d02b29022100c1e625d445fd26a983d09e81be4ed892752223f08fa509d50b718c652fcfdc25022100b84b26f6f3881aacab8ed14ebc243ba11560459e4014c2f3d4e6eb3e277b2eb7022055804a201fb8b0aac1506604ea495460c8bc044318e47ac0722a60f161b2f3ed0220389b4cd299a6fa9a82ef9136dfef3929974944d900d1dfb7c9734c5a8102032d02202bd8051a9a15f2bda7ec16cc065f3ccab7bd1c804e4d1a43214296e2131e05fc";
	protected static String strServerPublicHex="305c300d06092a864886f70d0101010500034b0030480241008b965720dcb8ddb3bf063be1cefc729f56843989d8c25f8ef3fc493ef2b65b9727ccad9c027258a2c8cebc4cf4e6d0a4a1bdd457cec116de44f7764aefec04730203010001";

	protected static String strClientPrivateHex="30820155020100300d06092a864886f70d01010105000482013f3082013b02010002410084049eb1a20cd2bef07317568e6f6f7daddebad32e7b411380ecd680afcd837050a94a2c8ae59d2601d8d273e7b1d994be4584e5bdced995fcc14141bd626b11020301000102410081dcd58a20c10271e6af200ef9efae396fd63feacab75dad64ce14b6e3b882888858c940501ba1953b4bae75221b03168eefc8f273b9fd1d430128e47060e7ad022100bc2c341a3bb06e7165886991d49ea56ca5db63bb47dc13eae7f41f087522b23f022100b39ab32210a3f8a50040d208574df85bd2635f0faf778423303938ad720ceeaf022020a32e1a4ab60c41e0d16b361f01b14189f6e6726a4152b710aa37e3b8a55ef3022100964b6c7fa32133df0fb92e89605594f171bb4a3f5bfc4d8d0cbbb59c1f06876d02206baf6100764c35e6b466c0e06d032a6142ea5c64d0da4cabfc7e3bd45b0acf4c";
	protected static String strClientPublicHex="305c300d06092a864886f70d0101010500034b00304802410084049eb1a20cd2bef07317568e6f6f7daddebad32e7b411380ecd680afcd837050a94a2c8ae59d2601d8d273e7b1d994be4584e5bdced995fcc14141bd626b110203010001";
	protected static String strClientPassPhrase="";
	
	public static String getStrClientPassPhrase() {
		return strClientPassPhrase;
	}
	protected static String strIntegracionPublicHex="305c300d06092a864886f70d0101010500034b0030480241009f582760ea1147ec29d8bcf965292d382e98f6fe4e3a57f73360685acb7e2c1b49878aac0d3b33796c4124b6b6296785df3f3d837207afdf9cc6a332340f2cab0203010001";
	protected static String strIntegracionPassPhrase="maab2012";
	
	public static String getStrIntegracionPublicHex() {
		System.out.println(""+strIntegracionPublicHex.replace("\r\n", "").toString());
		return strIntegracionPublicHex.replace("\r\n", "");
	}
	public static void setStrIntegracionPublicHex(String strIntegracionPublicHex) {
		System.out.println(""+strIntegracionPublicHex.replace("\r\n", "").toString());
		SecurityData.strIntegracionPublicHex = strIntegracionPublicHex;
	}
	public static String getStrIntegracionPassPhrase() {
		System.out.println(""+strIntegracionPassPhrase.replace("\r\n", "").toString());
		return strIntegracionPassPhrase.replace("\r\n", "");
	}
	public static void setStrIntegracionPassPhrase(String strIntegracionPassPhrase) {
		
		SecurityData.strIntegracionPassPhrase = strIntegracionPassPhrase;
	}
	public static String getStrServerPrivateHex() {
		System.out.println(""+strServerPrivateHex.replace("\r\n", "").toString());
		return strServerPrivateHex.replaceAll("(\\r|\\n)", "");
		
	}
	public static void setStrServerPrivateHex(String strServerPrivateHex) {
		System.out.println(""+strServerPrivateHex.replace("\r\n", "").toString());
			
		SecurityData.strServerPrivateHex = strServerPrivateHex;
	}
	public static String getStrServerPublicHex() {
		System.out.println(""+strServerPublicHex.replace("\r\n", "").toString());
		
		return strServerPublicHex.replaceAll("(\\r|\\n)", "");
	}
	public static void setStrServerPublicHex(String strServerPublicHex) {

		SecurityData.strServerPublicHex = strServerPublicHex;
	}
	public static String getStrClientPrivateHex() {
		System.out.println(""+strClientPrivateHex.replace("\n", "").toString());
		strClientPrivateHex=strClientPrivateHex.replaceAll("(\\r|\\n)", "");
		return strClientPrivateHex.replaceAll("(\\r|\\n)", "");
	}
	public static void setStrClientPrivateHex(String strClientPrivateHex) {
		SecurityData.strClientPrivateHex = strClientPrivateHex;
	}
	public static String getStrClientPublicHex() {
		System.out.println(""+strClientPublicHex.replace("\r\n", "").toString());
		
		return strClientPublicHex.replaceAll("(\\r|\\n)", "");
	}
	public static void setStrClientPublicHex(String strClientPublicHex) {
		SecurityData.strClientPublicHex = strClientPublicHex;
	}

	
	
	public PublicKeyCrypto getCryptor() {
		return cryptor;
	}
	public void setCryptor(PublicKeyCrypto cryptor) {
		this.cryptor = cryptor;
	}
}
