package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Reply;

public class ReplyValidator {
    public static List<String> validate(Reply e) {
        List<String> errors = new ArrayList<String>();


        String reply_error = _validateReply(e.getReply());
        if(!reply_error.equals("")) {
            errors.add(reply_error);
        }



        return errors;
}


        private static String _validateReply(String reply) {
            if(reply == null || reply.equals("")) {
                return "内容を入力してください";
            }

        return "";
    }




}