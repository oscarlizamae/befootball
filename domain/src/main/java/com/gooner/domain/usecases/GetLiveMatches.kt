package com.gooner.domain.usecases

import com.gooner.domain.model.Fixture
import com.gooner.domain.util.ResponseResult

interface GetLiveMatches {
    suspend operator fun invoke(): ResponseResult<List<Fixture>>
}