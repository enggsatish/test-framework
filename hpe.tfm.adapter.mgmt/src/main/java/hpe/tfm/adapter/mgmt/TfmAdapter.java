package hpe.tfm.adapter.mgmt;


import java.util.Map;

import hpe.tfm.adapter.mgmt.jms.TfmJmsAdapater;
import hpe.tfm.adapter.mgmt.jms.impl.TfmJmsQueueAdapaterImpl;
import hpe.tfm.adapter.mgmt.jms.impl.TfmJmsTopicAdapaterImpl;
import hpe.tfm.adapter.mgmt.ldap.TfmLdapAdapter;
import hpe.tfm.adapter.mgmt.ldap.impl.TfmLdapAdapterImpl;

public enum TfmAdapter {
	
	instance;
	
	public static Map<String, String> sendRequest(Map<String, String> reqMap) {
		return null;
	}
	
	public static TfmJmsAdapater jmsQueue() {
		return new TfmJmsQueueAdapaterImpl();
	}
	
	public static TfmJmsAdapater jmsTopic() {
		return new TfmJmsTopicAdapaterImpl();
	}
	
	public static TfmLdapAdapter ldap() {
		return new TfmLdapAdapterImpl();
	}
}
