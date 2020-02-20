package automation.PestRoutes.Controller.Subscriptions;

import automation.PestRoutes.PageObject.Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_Header;
import automation.PestRoutes.PageObject.CustomerOverview.CustomerViewDialog_SubscriptionTab;
import automation.PestRoutes.Utilities.BaseClass;
import automation.PestRoutes.Utilities.Reporter;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

public class AddSubscription extends BaseClass {

	CustomerViewDialog_SubscriptionTab subscription = new CustomerViewDialog_SubscriptionTab();
	CustomerViewDialog_Header customerDialogHeader;
	Header header;

	private String ticketItem = "bed";
	private String initialQuote = "120.00";
	private String initialDiscount = "20.00";
	private String recurringInvoice = "89.00";

	@Test
	public void validateSubscription() throws Exception {

		startSubscription();
		validatePreferredDayAppt();
		validateInitialInvoice();
		validateRecurringInvoice();

	}

	public void startSubscription() throws Exception {
		customerDialogHeader = new CustomerViewDialog_Header();
		header = new Header();
		header.Search_A_Customer(getData("userID", generalData));
		customerDialogHeader.NavigateTo(customerDialogHeader.subscriptionTabInDialog);
		subscription.clickNewSubscriptionButton();
		subscription.selectServiceType(getData("quarterly", quarterlyPreferredDayData));
		subscription.setCustomDate(getData("customDate", quarterlyPreferredDayData));
	}

	public void validatePreferredDayAppt() throws Exception {
		String[] prefferedDay = { getData("monday", quarterlyPreferredDayData), getData("tuesday", quarterlyPreferredDayData),
				getData("wednesday", quarterlyPreferredDayData), getData("thursday", quarterlyPreferredDayData), 
				getData("friday", quarterlyPreferredDayData),getData("saturday", quarterlyPreferredDayData), 
				getData("sunday", quarterlyPreferredDayData) };

		for (int i = 0; i < prefferedDay.length; i++) {
			System.out.println(i);
			subscription.selectPreferredDayOption(prefferedDay[i]);
			String[] actualUpComingDates = { subscription.getUpcomingAppt(subscription.firstUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.secondUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.thirdUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.fourthUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.fifthUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.sixthUpcomingAppointment),
					subscription.getUpcomingAppt(subscription.seventhUpcomingAppointment) };
			String[] expectedUpComingDates = { getData("customDate", quarterlyPreferredDayData),
					getData("first" + prefferedDay[i], quarterlyPreferredDayData), 
					getData("second" + prefferedDay[i], quarterlyPreferredDayData),
					getData("third" + prefferedDay[i], quarterlyPreferredDayData), 
					getData("fourth" + prefferedDay[i], quarterlyPreferredDayData),
					getData("fifth" + prefferedDay[i], quarterlyPreferredDayData), 
					getData("sixth" + prefferedDay[i], quarterlyPreferredDayData) };
			String[] daySlot = { " first slot", " 2nd slot", " 3rd slot", " 4th slot", " 5th slot", "6th slot",
					" 7th slot" };

			for (int j = 0; j < daySlot.length; j++) {
				System.out.println(actualUpComingDates[j]);
				System.out.println(expectedUpComingDates[j]);
				// assertTrue(actualUpComingDates[j].contains(expectedUpComingDates[j]));
				Reporter.status(expectedUpComingDates[j], actualUpComingDates[j],
						"Validate subscription by " + prefferedDay[i] + daySlot[j]);

			}
		}
	}

	public void validateInitialInvoice() {
		subscription.setInitialServiceQuote(initialQuote);
		subscription.setInitialServiceDiscount(initialDiscount);
		subscription.selectAdditionalItem_ToInitialInvoice(ticketItem);
		double finalInitialQuote = Double.parseDouble(initialQuote);
		double finalInitialDiscount = Double.parseDouble(initialDiscount);
		double ticketAmount = subscription.getInitialService_NewTicketItemPrice(ticketItem);
		double actualInitialSubtotal = subscription.getInitialSubTotal();
		double initialTax = subscription.getInitialTax();
		double initialTotal = subscription.getInitialTotal();

		double subTotal = finalInitialQuote + ticketAmount - finalInitialDiscount;
		String expectedSubTotal = Double.toString(subTotal);
		String actual_InitialSubTotal = Double.toString(actualInitialSubtotal);
		Reporter.status(expectedSubTotal, actual_InitialSubTotal, "Initial invoice sub total validation");
		double total = subTotal + initialTax;
		String expectedInitialTotal = Double.toString(total);
		String actualInitialTotal = Double.toString(initialTotal);
		Reporter.status(expectedInitialTotal, actualInitialTotal, "Initial invoice total validation");
	}

	public void validateRecurringInvoice() throws Exception {
		subscription.setServiceQuote(getData("quarterly", quarterlyPreferredDayData), initialQuote);
		subscription.selectAdditionalItem_ToRecurringInvoice(ticketItem);
		double serviceAmount = Double.parseDouble(recurringInvoice);
		double ticketAmount = subscription.getRecurringService_NewTicketItemPrice(ticketItem);
		double actualServiceSubtotal = subscription.getRecurringSubTotal();
		double serviceTax = subscription.getRecurringTax();
		double serviceTotal = subscription.getRecurringTotal();

		double subTotal = serviceAmount + ticketAmount;
		String expectedSubTotal = Double.toString(subTotal);
		String actual_ServiceSubTotal = Double.toString(actualServiceSubtotal);
		Reporter.status(expectedSubTotal, actual_ServiceSubTotal, "Service invoice sub total validation");
		double total = subTotal + serviceTax;
		String expectedServiceTotal = Double.toString(total);
		String actualServiceTotal = Double.toString(serviceTotal);
		Reporter.status(expectedServiceTotal, actualServiceTotal, "Service invoice total validation");
	}

}
