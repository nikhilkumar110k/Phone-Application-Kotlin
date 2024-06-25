package com.example.myapplication.ViewModel
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.room.User
import com.example.myapplication.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel(), androidx.databinding.Observable {

    val user=repository.users
    private var isUpdateOrDelete= false
    private lateinit var userToUpdateorDelete: User

    @Bindable
    val inputName= MutableLiveData<String?>()

    @Bindable
    val inputEmail= MutableLiveData<String?>()

    @Bindable
    val saveorUpdateButtonText= MutableLiveData<String>()


    @Bindable
    val clearAllOrDeleteButtonText= MutableLiveData<String>()


    init {
        saveorUpdateButtonText.value="Save"
        clearAllOrDeleteButtonText.value= "Delete"
    }

    fun saveOrUpdate(){
        val name= inputName.value!!
        val email= inputEmail.value!!

        insert(User(0,name,email))

        inputName.value=null
        inputEmail.value=null
    }

    fun clearAllOrDelete(){
        clearAll()
    }

    fun insert(user: User)= viewModelScope.launch {
        repository.insert(user)
    }

    fun clearAll()= viewModelScope.launch{
        repository.deleteAll()
    }

    fun update(user: User)= viewModelScope.launch{
        repository.update(user)
    }

    fun delete(user: User)= viewModelScope.launch {
        repository.delete(user)
    }


    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }


    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {

    }

}