package hpe.tfm.service.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VideoServiceUtil {

	public static Map<String, String> generateParamList() {
		Map<String, String> paramMap = new HashMap<String, String>();
		Arrays.asList(EsfKeyConstants.values())
				.forEach(paramKeyList -> paramMap.put(paramKeyList.getKey(), paramKeyList.getAlias()));
		return paramMap;
	}
	
	public static Map<String, String> generateRuleList() {
		Map<String, String> paramRuleMap = new HashMap<String, String>();
		paramRuleMap.put("Header", "Message");
		paramRuleMap.put("ENVIRONMENTNAME", "Header");
		paramRuleMap.put("X_SHAW_ORIGINATING_USER_ID", "Header");
		paramRuleMap.put("X_SHAW_TRANSACTION_ID", "Header");
		paramRuleMap.put("X_SHAW_SERVICE_ORCHESTRATION_TRANSACTION_ID", "Header");
		paramRuleMap.put("JMSCorrelationID", "Header");
		paramRuleMap.put("JMSReplyTo", "Header");
		paramRuleMap.put("Body", "Message");
		paramRuleMap.put("ServiceActivationRequest", "Body");
		paramRuleMap.put("TechnicalContext", "ServiceActivationRequest");
		paramRuleMap.put("MessageId", "TechnicalContext");
		paramRuleMap.put("MessageSentTimestampUTC", "TechnicalContext");
		paramRuleMap.put("Property", "TechnicalContext");
		paramRuleMap.put("ServiceOrder", "ServiceActivationRequest");
		paramRuleMap.put("CustomerAccountInteractionRole/CustomerAccountRef/AccountId", "ServiceOrder");
		paramRuleMap.put("ServiceOrderItems", "ServiceOrder");
		paramRuleMap.put("ServiceOrderItem", "ServiceOrderItems");
		paramRuleMap.put("BusinessInteractionLocation/AmericanPropertyAddress/AmericanPropertyAddressProperties/AddressId", "ServiceOrderItem");
		paramRuleMap.put("ServiceOrderItemProperties/Action", "ServiceOrderItem");
		paramRuleMap.put("ResourceFacingService", "ServiceOrderItem");
		paramRuleMap.put("ResourceRefs", "ResourceFacingService");
		paramRuleMap.put("ResourceRef/ObjectId", "ResourceRefs");
		paramRuleMap.put("ResourceFacingServiceProperties/ServiceId", "ResourceFacingService");		
		paramRuleMap.put("ResourceFacingServiceSpec", "ResourceFacingService");
		paramRuleMap.put("ServiceSpecClassification/ServiceSpecClassificationName", "ResourceFacingServiceSpec");
		paramRuleMap.put("ResourceFacingServiceSpecProperties/ServiceSpecId", "ResourceFacingServiceSpec");		
		paramRuleMap.put("ServiceCharacteristics", "ResourceFacingService");
		paramRuleMap.put("Characteristic", "ServiceCharacteristics");
		paramRuleMap.put("CharacteristicName", "Characteristic");
		paramRuleMap.put("CharacteristicCategory/CategoryName", "Characteristic");
		paramRuleMap.put("CharacteristicValue/Value", "Characteristic");
		return paramRuleMap;
	}
}
