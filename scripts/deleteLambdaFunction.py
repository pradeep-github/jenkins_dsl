import boto3

import argparse

parser = argparse.ArgumentParser()
parser.add_argument("name", help="lambda function name")

args = parser.parse_args()

session = boto3.Session()
lambda_client = session.client('lambda')

lambda_client.delete_function(
	FunctionName=args.name
)
