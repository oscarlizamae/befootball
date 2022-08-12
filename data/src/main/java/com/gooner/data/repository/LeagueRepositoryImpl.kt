package com.gooner.data.repository

import com.gooner.data.BeFootballApi
import com.gooner.data.remote.mapper.LeagueDtoMapper
import com.gooner.domain.model.League
import com.gooner.domain.repository.LeagueRepository
import com.gooner.domain.util.ResponseResult
import java.lang.Exception
import java.util.*

class LeagueRepositoryImpl(
    private val beFootballApi: BeFootballApi,
    private val leagueDtoMapper: LeagueDtoMapper
) : LeagueRepository {
    override suspend fun getCurrentLeagues(): ResponseResult<List<League>> {
        return try {
            val result = beFootballApi.getCurrentLeagues(
                season = Calendar.getInstance().get(Calendar.YEAR).toString()
            )
            return ResponseResult.Success(
                leagueDtoMapper.mapFromEntityList(result.response)
            )
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }
}