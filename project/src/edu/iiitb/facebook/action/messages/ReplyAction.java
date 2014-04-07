package edu.iiitb.facebook.action.messages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.action.dao.MessageDAO;
import edu.iiitb.facebook.action.dao.impl.MessageDAOImpl;
import edu.iiitb.facebook.action.dao.impl.UserDAOImpl;
import edu.iiitb.facebook.action.model.Message;
import edu.iiitb.facebook.action.model.User;
import edu.iiitb.facebook.util.Constants;

public class ReplyAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = 1218149774198859476L;

	private Message replyMsg;
	private String now;
	private Map<String, Object> session;
	
	/**
	 * Send reply
	 * 
	 * @return
	 */
	public String reply()
	{
		if (session == null)
			return LOGIN;
			
		User thisUser = (User)(session.get(Constants.USER));
		replyMsg.setSender(thisUser.getUserId());
		replyMsg.setSenderFirstName(thisUser.getFirstName());
		replyMsg.setSenderLastName(thisUser.getLastName());

		User toUser = new UserDAOImpl().getUserByUserId(replyMsg.getRecipient());
		replyMsg.setRecipientFirstName(toUser.getFirstName());
		replyMsg.setRecipientLastName(toUser.getLastName());

		MessageDAO dao = new MessageDAOImpl();
		dao.insert(replyMsg);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	    setNow(sdf.format(new Date()));

		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;

	}

	public Message getReplyMsg()
	{
		return replyMsg;
	}

	public void setReplyMsg(Message replyMsg)
	{
		this.replyMsg = replyMsg;
	}

	public String getNow()
	{
		return now;
	}

	public void setNow(String now)
	{
		this.now = now;
	}
}
