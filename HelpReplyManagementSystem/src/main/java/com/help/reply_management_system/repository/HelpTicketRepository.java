package com.help.reply_management_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.help.reply_management_system.entity.HelpTicket;

public interface HelpTicketRepository extends JpaRepository<HelpTicket, Long> {

}