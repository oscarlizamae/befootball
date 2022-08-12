package com.gooner.domain.repository

import com.gooner.domain.model.League
import com.gooner.domain.util.ResponseResult

interface LeagueRepository {
    suspend fun getCurrentLeagues(): ResponseResult<List<League>>
}