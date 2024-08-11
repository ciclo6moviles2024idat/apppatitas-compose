package pe.idat.apppatitas_compose.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.home.data.network.request.VoluntarioRequest
import pe.idat.apppatitas_compose.home.data.network.response.VoluntarioResponse
import pe.idat.apppatitas_compose.home.domain.VoluntarioUseCase
import javax.inject.Inject

@HiltViewModel
class VoluntarioViewModel @Inject constructor(
    private val voluntarioUseCase: VoluntarioUseCase) : ViewModel() {

        private val _idpersona = MutableLiveData<Int>()
    val idpersona: LiveData<Int> = _idpersona
    private val _voluntarioResponse = MutableLiveData<VoluntarioResponse>()
    val voluntarioResponse: LiveData<VoluntarioResponse> = _voluntarioResponse

    fun registrarVoluntario(){
        viewModelScope.launch {
            val response  = voluntarioUseCase(
                VoluntarioRequest(idpersona.value!!)
            )
            _voluntarioResponse.value = response
        }
    }

}