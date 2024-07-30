package com.deliverymate.service;

import org.springframework.stereotype.Service;

@Service
public class FortOneService {

    private final String PORT_ONE_IMP_KEY = "6626485841251685";
    private final String PORT_ONE_IMP_SECRET = "E1zglzwjWpjLFUQnAyIjGhRBxwQu9tRwjhkgz7yhAPMEWsoIZyk0SFo5XZi2htLVj4sTM6nP7Q9mnZvB";
    private final String PORT_ONE_ACCESS_TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String PORT_ONE_USER_CERT_INFO_URL = "https://api.iamport.kr/certifications/{impUid}";

}
