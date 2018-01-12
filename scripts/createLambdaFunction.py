import boto3

import argparse

parser = argparse.ArgumentParser()
parser.add_argument("name", help="lambda function name")
parser.add_argument("role", help="role name")
parser.add_argument("--runtime", default="python2.7", help="runtime name")
parser.add_argument("--memory", default="128", help="memory size")

args = parser.parse_args()

session = boto3.Session()
lambda_client = session.client('lambda')

lambda_client.create_function(
	FunctionName=args.name,
	Runtime=args.runtime,
	Role=args.role,
	Handler="{0}.lambda_handler".format(args.name),
	Code={'ZipFile': open("{0}.zip".format(args.name), 'rb').read(),},
)
