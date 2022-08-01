package ru.something.weatherandroidapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.something.weatherandroidapp.activities.AppState
import ru.something.weatherandroidapp.model.FactDTO
import ru.something.weatherandroidapp.model.Weather
import ru.something.weatherandroidapp.model.WeatherDTO
import ru.something.weatherandroidapp.model.getDefaultCity
import ru.something.weatherandroidapp.repository.*

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class WeatherViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository =
        DetailsRepositoryImpl(RemoteDataSource())
) : ViewModel() {
    fun getLiveData() = detailsLiveData
    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    private val callBack = object :
        Callback<WeatherDTO> {
        override fun onResponse(
            call: Call<WeatherDTO>, response:
            Response<WeatherDTO>
        ) {
            val serverResponse: WeatherDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            detailsLiveData.postValue(
                AppState.Error(
                    Throwable(
                        t.message ?: REQUEST_ERROR
                    )
                )
            )
        }

        private fun checkResponse(serverResponse: WeatherDTO): AppState {
            val fact = serverResponse.fact
            return if (fact == null || (fact.temp == null) || (fact.feels_like ==
                        null) || fact.condition.isNullOrEmpty()
            ) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertWeatherDtoToModel(serverResponse))
            }
        }
    }

    fun convertWeatherDtoToModel(weatherDTO: WeatherDTO): List<Weather> {
        val factDTO: FactDTO = weatherDTO.fact!!
        return listOf(
            Weather(
                getDefaultCity(), factDTO.temp!!, factDTO.feels_like!!,
                factDTO.condition!!
            )
        )
    }
}



