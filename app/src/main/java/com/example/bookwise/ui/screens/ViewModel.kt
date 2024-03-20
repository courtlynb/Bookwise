package com.example.bookwise.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.bookwise.model.Book
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookwise.data.Repository
import com.example.bookwise.ui.Application
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface UiState {
    data class Success(val books: List<Book>) : UiState
    object Loading : UiState
    object Error : UiState

}

class ViewModel(private val repository: Repository) : ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            uiState = UiState.Loading
            uiState = try {
                UiState.Success(repository.getBooks())
            } catch (e: IOException) {
                UiState.Error
            } catch (e: HttpException) {
                UiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as Application)
                val repository = application.container.repository
                ViewModel(repository = repository)
            }
        }
    }
}