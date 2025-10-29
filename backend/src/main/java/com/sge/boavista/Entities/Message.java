package com.sge.boavista.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message<T> {


    public int code;
    public T message;
    public boolean sucess;

}
