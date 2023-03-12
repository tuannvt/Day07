package mvc.service;

import mvc.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import mvc.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    private JpaTransactionManager transactionManager;

    // Dependency injection via constructor
    public AccountService(JpaTransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }
    public AccountService(){
    }


    public void transferMoney(int sourceAccountId, int targetAccountId, double amount){
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            AccountEntity sourceAcc = accountRepository.findById(sourceAccountId).get();
            AccountEntity targetAcc = accountRepository.findById(targetAccountId).get();

            // Calculation amount for account source
            sourceAcc.setBalance(sourceAcc.getBalance() - amount);
            // save to DB
            accountRepository.save(sourceAcc);
            // Check condition for account source
            if (sourceAcc.getBalance() < 0){
                throw new Exception("amount to transfer more than balance");
            }

            // Calculation amount for account target
            targetAcc.setBalance(targetAcc.getBalance() + amount);
            // save to DB
            accountRepository.save(targetAcc);

            // Commit transaction
            transactionManager.commit(status);

        }catch (Exception e){
            transactionManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class, noRollbackFor = NullPointerException.class)
   // @Transactional
    public void transferMoneyUsingAnnotion(int sourceAccountId, int targetAccountId, double amount) throws Exception {
        AccountEntity sourceAcc = accountRepository.findById(sourceAccountId).get();
        AccountEntity targetAcc = accountRepository.findById(targetAccountId).get();

        // Calculation amount for account source
        sourceAcc.setBalance(sourceAcc.getBalance() - amount);
        // save to DB
        accountRepository.save(sourceAcc);
        // Check condition for account source
        if (sourceAcc.getBalance() < 0){
            throw new Exception("amount to transfer more than balance");
        }

        // Calculation amount for account target
        targetAcc.setBalance(targetAcc.getBalance() + amount);
        // save to DB
        accountRepository.save(targetAcc);
    }
    //@Transactional(noRollbackFor = NullPointerException.class)
    public void updateAccLook(){
        //balaal
        //accountRepository.updateAccLook();
    }
   // @Transactional(rollbackFor = NullPointerException.class)
    public void deleteAccLook(){
        //balaal
        //accountRepository.deleteAccByBalanc();
    }

}
