package com.gooner.domain.usecases

import com.gooner.domain.model.Fixture
import com.gooner.domain.repository.FixtureRepository
import com.gooner.domain.util.ResponseResult

class GetFixtureDetailsImpl(
    private val fixtureRepository: FixtureRepository
) : GetFixtureDetails {
    override suspend fun invoke(fixtureId: Int): ResponseResult<Fixture> {
        return fixtureRepository.getFixtureDetails(fixtureId)
    }
}