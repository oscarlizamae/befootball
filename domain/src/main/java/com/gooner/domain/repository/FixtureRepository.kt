package com.gooner.domain.repository

import com.gooner.domain.model.Fixture
import com.gooner.domain.util.ResponseResult

interface FixtureRepository {
    suspend fun getLiveFixtures(): ResponseResult<List<Fixture>>
}