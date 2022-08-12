package com.gooner.domain.usecases

import com.gooner.domain.model.Fixture
import com.gooner.domain.repository.FixtureRepository
import com.gooner.domain.util.ResponseResult

class GetLiveMatchesImpl(
    private val fixtureRepository: FixtureRepository
) : GetLiveMatches {
    override suspend fun invoke(): ResponseResult<List<Fixture>> {
        return fixtureRepository.getLiveFixtures()
    }
}