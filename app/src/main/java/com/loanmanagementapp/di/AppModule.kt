package com.loanmanagementapp.di

import android.content.Context
import android.content.SharedPreferences
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.data.LoanService
import com.loanmanagementapp.data.LoginUseCase
import com.loanmanagementapp.data.MockLoanService
import com.loanmanagementapp.utils.PreferenceManagerImpl
import com.loanmanagementapp.utils.strategy.calculate.DefaultCalculateStrategy
import com.loanmanagementapp.utils.strategy.calculate.LoanCalculatorProvider
import com.loanmanagementapp.utils.strategy.processing.DecreaseDueInStrategy
import com.loanmanagementapp.utils.strategy.processing.InterestIncreaseStrategy
import com.loanmanagementapp.utils.strategy.processing.LoanProcessingContext
import com.loanmanagementapp.utils.strategy.processing.LoanUpdateStrategy
import com.loanmanagementapp.utils.strategy.processing.OverdueLoanStrategy
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
    fun provideLoanRepository(
        loanService: LoanService,
        strategies: LoanProcessingContext
    ): LoanRepository {
        return LoanRepository(loanService, strategies)
    }

    @Provides
    @Singleton
    fun provideLoanUpdateContext(
        strategies: Set<@JvmSuppressWildcards LoanUpdateStrategy>
    ): LoanProcessingContext {
        return LoanProcessingContext(strategies)
    }

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

    @Provides
    @Singleton
    fun provideLoginUseCase(
        preferenceManager: PreferenceManagerImpl
    ): LoginUseCase {
        return LoginUseCase(preferenceManager)
    }

    @Provides
    fun provideLoanStrategies(): Set<@JvmSuppressWildcards LoanUpdateStrategy> = setOf(
        OverdueLoanStrategy(),
        InterestIncreaseStrategy(),
        DecreaseDueInStrategy()
    )
}
