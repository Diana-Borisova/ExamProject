package com.example.foodplanner.service;




import com.example.foodplanner.model.entity.Log;

import java.util.List;
import java.util.Map;

public interface LogService {
    void createLog(String action,String exception);

    void createRegisterLog(Long result);

    void cleanLog();

    void cleanExceptions();

    List<Log> getRegisterLog();

    List<Log> getExceptionLog();

    Map<String,Integer> getRegisterStats();

}
