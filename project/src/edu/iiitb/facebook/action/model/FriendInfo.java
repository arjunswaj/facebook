package edu.iiitb.facebook.action.model;

public class FriendInfo
{

	public enum RequestStatus
	{
		PENDING("pending"), ACCEPTED("accepted"), BLOCKED("blocked"), CONFIRM_REQUEST("confirm_request"), ADD_FRIEND("add_friend"), MYPROFILE(
				"myprofile");

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

	int requestedBy;
	int requestFor;

	RequestStatus requestStatus;

}
