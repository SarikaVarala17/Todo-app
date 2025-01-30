package org.example.di

import dagger.Component
import javax.inject.Singleton
import org.example.http.components.HttpComponent
import org.example.http.HttpModule
import org.example.data.di.AppModule

@Singleton
@Component(modules = [HttpModule::class])
interface AppComponent {

}
