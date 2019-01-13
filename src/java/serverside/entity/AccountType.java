/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside.entity;

/**
 * Accounts types for Account instances: standard or credit type.
 * @author javi
 */
public enum AccountType {
    /**
     * Type of account used by Customers to keep savings an allows debit 
     * withdrawals, deposit and funds transfers.
     */
    STANDARD,
    /**
     * Type of account used by Customers to make payments, charges and funds transfers.
     * This type includes a credit limit greater than zero that allows credit operations 
     * to that limit. 
     */
    CREDIT;
}
