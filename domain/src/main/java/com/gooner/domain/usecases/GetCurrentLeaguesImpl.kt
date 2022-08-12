package com.gooner.domain.usecases

import com.gooner.domain.model.League
import com.gooner.domain.repository.LeagueRepository
import com.gooner.domain.util.ResponseResult

class GetCurrentLeaguesImpl(
    private val leaguesRepository: LeagueRepository
) : GetCurrentLeagues {
    override suspend fun invoke(): ResponseResult<List<League>> {
        return leaguesRepository.getCurrentLeagues()
    }
}