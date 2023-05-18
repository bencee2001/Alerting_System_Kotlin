package com.bme.kotlin.reportservice.Errors

class NoSuchEventTypeException:RuntimeException{

    constructor():super()

    constructor(msg:String):super(msg)

}