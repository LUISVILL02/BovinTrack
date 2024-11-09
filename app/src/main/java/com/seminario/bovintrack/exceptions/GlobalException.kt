package com.seminario.bovintrack.exceptions

class GlobalException(val erroCode: Int, val errorMessage: String) : Exception(errorMessage)