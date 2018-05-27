/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Sms;
import Iservices.ISms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsService implements ISms{
    public static final String ACCOUNT_SID = "ACdac234a307927307f4ee9f0ea6213f51";
    public static final String AUTH_TOKEN = "66ba97784e0e462c158222571472c2c3";
    @Override
    public void sendSms(Sms sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message msg = Message.creator(new PhoneNumber(sms.getNum()), new PhoneNumber("+18455384451"), sms.getMessagetel()).create();
            System.out.println(msg.getSid());
}
}