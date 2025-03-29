package com.loanmanagementapp.di

import android.content.Context
import android.content.SharedPreferences
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.data.LoanService
import com.loanmanagementapp.data.MockLoanService
import com.loanmanagementapp.utils.LoanCalculatorProvider
import com.loanmanagementapp.utils.strategy.DefaultCalculateStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoanService(): LoanService = MockLoanService()

    @Provides
    @Singleton
    fun provideLoanRepository(loanService: LoanService): LoanRepository = LoanRepository(loanService)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLoanCalculatorProvider(): LoanCalculatorProvider {
        return LoanCalculatorProvider(provideDefaultCalculateStrategy())
    }

    @Provides
    @Singleton
    fun provideDefaultCalculateStrategy(): DefaultCalculateStrategy {
        return DefaultCalculateStrategy()
    }
}
