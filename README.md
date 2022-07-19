# TallyChain

TallyChain is a private blockchain which stores the immutable invoice records of the participating businesses. It does not stores actual invoice data or Personally Identifiable Information (PII) of the participating party (to secure their privacy and be compliant with GDPR), but it only stores the meta dat aand signatures, just enough to validate the records against the actual records of the participating businesses. 

TallyChain stores data in distributed  full nodes of blockchains. the nodes are bootstrapped or killed as need basis. At any point, the participating nodes IP is exposed using Seed DNS Name (Public name), so that any participating node can communicate with any node randomly (vial the TallyChain OpenAPI) using this public DNS name. 
