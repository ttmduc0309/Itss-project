package subsystem.interbank;

import common.exception.interbank.UnrecognizedException;

public class InterbankBoundry {
	String query(String url, String data) {
        String response = null;
        try {
            //response = API.post(url, data);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new UnrecognizedException();
        }
        return null;
    }
}
