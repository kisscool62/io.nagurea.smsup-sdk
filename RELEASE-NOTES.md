## [0.1.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/2bda7c584bd4d05b37b68d911ae9a03e21509e68...v0.1.0) (2022-04-15)

Initial version with:
* Send SMS > Single message
* Send SMS > Campaign
* Send SMS > Simulate a single message

## [0.2.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.1.0...v0.2.0) (2022-06-02)

* Lists > Create list
* Lists > Get lists
* Lists > Get list

## [0.3.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.2.0...v0.3.0) (2022-06-10)

* Send SMS > Campaign with list
* Send SMS > Simulate a campaign

## [0.4.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.3.0...v0.4.0) (2022-06-16)
* Send SMS > Cancel a campaign

## [0.5.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.4.0...v0.5.0) (2022-06-29)
* Send SMS > Short URLs for campaign and campaign lists

## [0.6.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.5.0...v0.6.0) (2022-07-01)
* Lists > Delete list
* Lists > Clear list
* Lists > blacklist
* Lists > npai

## [0.7.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.6.0...v0.7.0) (2022-07-04)
* Campaigns > Get campaigns history
* Campaigns > Get campaign
* Campaigns > Blacklist the NPAI of a campaign
* Campaigns > Get campaign stops
* Campaigns > Get campaign npais
* Campaigns > Get campaign replies

## [0.8.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.7.0...v0.8.0) (2022-07-12)
* Contacts > Insert into a list
* Contacts > Deduplicate list
* Contacts > Remove contact
* Contacts > Update a contact
* Contacts > Add contacts to the blacklist
* Contacts > Add contacts to the NPAI list

## [0.9.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.8.0...v0.9.0) (2022-07-14)
* Notifications > Balance > Get
* Notifications > Balance > Update

## [0.10.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.9.0...v0.10.0) (2022-07-15)
* Invoices > Get invoices
* Invoices > Get invoice

## [0.10.1](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.10.0...v0.10.1) (2022-07-17)
* Fixed document Main example + set public accessor on *Service constructors for easier build without spring or dedicated factory

## [0.10.2](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.10.1...v0.10.2) (2022-08-08)
* Invoices > Download invoice

## [0.11.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.10.2...v0.11.0) (2022-08-09)
* API Token > Create a token (directly or from subaccount)

## [0.11.1](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.11.0...v0.11.1) (2022-08-11)
* API Token > Retrieve tokens

## [0.11.2](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.11.1...v0.11.2) (2022-08-12)
* API Token > Delete a token

## [0.12.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.11.2...v0.12.0) (2022-08-14)
* HLR Lookup

## [0.13.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.12.0...v0.13.0) (2022-08-15)
* Webhooks > Create a webhook

## [0.13.1](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.13.0...v0.13.1) (2022-08-17)
* Webhooks > Retrieve a webhook
* Webhooks > Retrieve a single webhook
* Webhooks > Delete a webhook
* Webhooks > Update a webhook

## [0.14.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.13.1...v0.14.0) (2022-09-22)
* Account Managing > Credits
* Account Managing > Create an account
* Added Recipients on Short URL campaigns (Send SMS > Short URL)
* Fixed recipients bugs on campaigns, create tokens, create webhook, HLR lookup, update webhook
* Fixed content type for every POST service (set to application/json instead of form url encoded)
* Account Managing > Retrieve an account
* Account Managing > Retrieve a sub-account
* Account Managing > Retrieve sub-accounts
* Account Managing > Lock a sub-account
* Account Managing > Unlock a sub-account
* Account Managing > Transfer credits
* Account Managing > Data retention > Get
* Account Managing > Data retention > Update

## [1.0.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v0.14.0...v1.0.0) (2022-09-26)
First release with every SMSUp API

## [1.0.0](https://github.com/kisscool62/io.nagurea.smsupsdk/compare/v1.0.0...v1.0.1) (2022-09-26)
Fixed [sonatype-2020-0926] CWE-379: Creation of Temporary File in Directory with Incorrect Permissions

### Upgrade Steps
* [NO ACTION REQUIRED]

### Breaking Changes

### New Features

### Bug Fixes
Fixed [sonatype-2020-0926] CWE-379: Creation of Temporary File in Directory with Incorrect Permissions
 