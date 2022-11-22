package com.gooner.data.repository

import android.util.Log
import com.gooner.data.BeFootballApi
import com.gooner.data.remote.mapper.FixtureDtoMapper
import com.gooner.domain.model.Fixture
import com.gooner.domain.repository.FixtureRepository
import com.gooner.domain.util.ResponseResult

class FixtureRepositoryImpl(
    private val beFootballApi: BeFootballApi,
    private val fixtureDtoMapper: FixtureDtoMapper
) : FixtureRepository {

    override suspend fun getLiveFixtures(): ResponseResult<List<Fixture>> {
        return try {
            val result = beFootballApi.getLiveFixtures()
            Log.d("League", result.response.toString())
            ResponseResult.Success(
                fixtureDtoMapper.mapFromEntityList(result.response)
            )
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }

    override suspend fun getFixtureDetails(fixtureId: Int): ResponseResult<Fixture> {
        return try {
            val result = beFootballApi.getFixtureDetails(fixtureId.toString())
            if (result.response.isNotEmpty()) {
                ResponseResult.Success(
                    fixtureDtoMapper.mapFromEntity(result.response[0])
                )
            } else {
                ResponseResult.Error(
                    Exception()
                )
            }
        } catch (e: Exception) {
            ResponseResult.Error(e)
        }
    }

}