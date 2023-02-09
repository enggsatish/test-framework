package hpe.tfm.service.util;

public enum EsfKeyConstants {

	message ("Message"),
	header ("Header"),
	envname ("ENVIRONMENTNAME"),
	userid ("X_SHAW_ORIGINATING_USER_ID"),
	tranid ("X_SHAW_TRANSACTION_ID"),
	otranid ("X_SHAW_SERVICE_ORCHESTRATION_TRANSACTION_ID"),
	jmscid ("JMSCorrelationID"),
	jmsreplyto ("JMSReplyTo"),
	body ("Body"),
	sar ("ServiceActivationRequest"),
	tc ("TechnicalContext"),
	mid ("MessageId"),
	msgtime ("MessageSentTimestampUTC"),
	prop ("Property"),
	name ("name"),
	type ("type"),
	value ("value"),
	so ("ServiceOrder"),
	accountid ("CustomerAccountInteractionRole/CustomerAccountRef/AccountId"),
	soitems ("ServiceOrderItems"),
	soitem ("ServiceOrderItem"),
	addressid ("BusinessInteractionLocation/AmericanPropertyAddress/AmericanPropertyAddressProperties/AddressId"),
	action ("ServiceOrderItemProperties/Action"),
	rfs ("ResourceFacingService"),
	rrefs ("ResourceRefs"),
	objectid ("ResourceRef/ObjectId"),
	serviceid ("ResourceFacingServiceProperties/ServiceId"),
	rfsspec ("ResourceFacingServiceSpec"),
	classificationname ("ServiceSpecClassification/ServiceSpecClassificationName"),
	servicespecid ("ResourceFacingServiceSpecProperties/ServiceSpecId"),
	servicecharacteristic ("ServiceCharacteristics"),
	characteristic ("Characteristic"),
	carname ("CharacteristicName"),
	catgname ("CharacteristicCategory/CategoryName"),
	catvalue ("CharacteristicValue/Value"),
	serialnumber ("SerialNumber")	
	;
	
	private String key;
	
	/** @TODO later to implement rule here*/
	private EsfKeyConstants parent;
	
	private EsfKeyConstants(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getAlias() {
		return this.name();
	}
}
