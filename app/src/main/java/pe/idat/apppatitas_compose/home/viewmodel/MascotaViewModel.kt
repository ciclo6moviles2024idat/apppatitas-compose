package pe.idat.apppatitas_compose.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.idat.apppatitas_compose.home.data.network.response.MascotaResponse
import pe.idat.apppatitas_compose.home.domain.MascotaUseCase
import javax.inject.Inject

@HiltViewModel
class MascotaViewModel @Inject constructor(
    private val mascotaUseCase: MascotaUseCase
) : ViewModel() {
    private val _mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    val mascotaResponse: LiveData<List<MascotaResponse>> = _mascotaResponse
    init {
        listarMascotas()
    }
    fun listarMascotas(){
        viewModelScope.launch {
            val response  = mascotaUseCase()
            _mascotaResponse.value = response
        }
    }
}