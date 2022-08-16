package com.gooner.domain.usecases

import com.gooner.domain.model.League
import com.gooner.domain.util.ResponseResult

interface GetCurrentLeagues {
    suspend operator fun invoke(): ResponseResult<List<League>>
}