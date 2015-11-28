package pl.vgtworld.resourceobserver.services;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Session;

@Stateless
public class MailService {

	@Resource(lookup = "java:jboss/mail/resource-observer-mail")
	private Session mailSession;

}
