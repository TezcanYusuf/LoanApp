package com.loanmanagementapp

import android.content.Context
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.data.LoanService
import com.loanmanagementapp.data.LoanStatus
import com.loanmanagementapp.utils.strategy.processing.LoanProcessingContext
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ExampleUnitTest {

    @Test
    fun updateLoans_shouldLoadUpdateAndSaveLoans() = runTest {
        // Arrange
        val context = mockk<Context>()
        val loanService = mockk<LoanService>()
        val updateContext = mockk<LoanProcessingContext>()

        val originalLoans = listOf(
            Loan("Test Loan 1", 100.0, 5.0, LoanStatus.ACTIVE, 12),
            Loan("Test Loan 1", 200.0, 4.0, LoanStatus.ACTIVE, 12),
        )
        val updatedLoans = listOf(
            Loan("Test Loan 1", 200.0, 4.0, LoanStatus.ACTIVE, 12),
            Loan("Test Loan 1", 200.0, 4.0, LoanStatus.ACTIVE, 12),
        )

        coEvery {  loanService.loadLoans(context) } returns originalLoans
        every { updateContext.applyAll(originalLoans[0]) } returns updatedLoans[0]
        every { updateContext.applyAll(originalLoans[1]) } returns updatedLoans[1]
        coEvery { loanService.saveLoans(updatedLoans) } just Runs

        val repository = LoanRepository(loanService, updateContext)

        // Act
        val result = repository.updateLoans(context)

        // Assert
        assertEquals(updatedLoans, result)
        coVerify { loanService.loadLoans(context) }
        verify { updateContext.applyAll(originalLoans[0]) }
        verify { updateContext.applyAll(originalLoans[1]) }
        coVerify { loanService.saveLoans(updatedLoans) }
        println("Loans updated: $result")
    }
}
