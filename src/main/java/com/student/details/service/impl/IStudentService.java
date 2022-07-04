package com.student.details.service.impl;

import com.student.details.model.response.ResponseModel;
import reactor.core.publisher.Mono;

public interface IStudentService {

    Mono<ResponseModel> getStudentByName(String name);

}
