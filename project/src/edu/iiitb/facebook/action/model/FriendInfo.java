package edu.iiitb.facebook.action.model;

public class FriendInfo
{

	public enum RequestStatus
	{
		PENDING("pending"), ACCEPTED("accepted"), BLOCKED("blocked"), UNBLOCKED("unblocked"), CONFIRM_REQUEST("confirm_request");

		public String reqstat;

		RequestStatus(String requestStatus)
		{
			reqstat = requestStatus;
		}

		public String getReqstat()
		{
			return reqstat;
		}

		public static RequestStatus fromString(String reqStat)
		{

			if (reqStat != null)
			{
				for (RequestStatus status : RequestStatus.values())
				{
					if (status.getReqstat().equals(reqStat))
					{
						return status;
					}

				}
			}
			return null;

		}

	}

	public int getRequestedBy()
	{
		return requestedBy;
	}

	public void setRequestedBy(int requestedBy)
	{
		this.requestedBy = requestedBy;
	}

	public int getRequestFor()
	{
		return requestFor;
	}

	public void setRequestFor(int requestFor)
	{
		this.requestFor = requestFor;
	}

	public RequestStatus getRequestStatus()
	{
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus)
	{
		this.requestStatus = requestStatus;
	}

	public RequestStatus getBlockedStatus()
	{
		return blockedStatus;
	}

	public void setBlockedStatus(RequestStatus blockedStatus)
	{
		this.blockedStatus = blockedStatus;
	}

	int requestedBy;
	int requestFor;

	RequestStatus requestStatus;

	RequestStatus blockedStatus;

}
