/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controllers;

/**
 *
 * @author Ronaldo Hanh
 */
public interface CardTestController {
   int testIssueCard(String userName, String day, String month, String year, String activateCode);
   int testSearchCard(String cardID, String userName, String fullName);
}
