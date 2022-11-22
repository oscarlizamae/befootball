package com.gooner.domain.usecases

import com.gooner.domain.model.Fixture
import com.gooner.domain.util.ResponseResult

interface GetFixtureDetails {
    suspend operator fun invoke(fixtureId: Int): ResponseResult<Fixture>
}