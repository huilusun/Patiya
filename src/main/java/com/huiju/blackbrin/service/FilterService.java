package com.huiju.blackbrin.service;

public interface FilterService {
    
    boolean auth(String user, String token);
    
    String getEspecialToken();
    
    boolean commonAuth(String jid, String token);
    
    /**
     * 若校验成功，则返回jid，否则返回null
     * @param realIp
     * @param token
     * @return
     */
    String coconutAuth(String realIp, String token) throws Exception;

}
